package com.rafiul.platzi2301.views.product

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.rafiul.platzi2301.R
import com.rafiul.platzi2301.databinding.FragmentProductUploadBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

@AndroidEntryPoint
class ProductUploadFragment : Fragment() {

    private val viewModel by viewModels<ProductViewModel>()

    private lateinit var binding: FragmentProductUploadBinding

    lateinit var imageUri: Uri
    var permissionGranted: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pIV.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(512, 512)
                .createIntent { intent -> startForProfileImageResult.launch(intent) }
        }

        binding.uploadBtn.setOnClickListener {
            permissionGranted?.let {
                if (it) {
                    uploadImage(imageUri)
                } else {
                    Toast.makeText(requireContext(), "Permission Required!!!!", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        viewModel.uploadFileResponse.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                Log.d("TAG", "{${it.body()}}")

                binding.apply {
                    responseLayout.visibility = View.VISIBLE
                    pIVOnline.load(it.body()?.location)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkPermission()
    }

    @SuppressLint("Recycle")
    private fun uploadImage(imageUri: Uri) {

        val fileDirectory = requireContext().filesDir
        val file = File(fileDirectory, "image_product_${UUID.randomUUID()}.png")
        val inputStream = requireActivity().contentResolver.openInputStream(imageUri)
        val outPutStream = FileOutputStream(file)

        inputStream?.copyTo(outPutStream)
        val requiredBody = file.asRequestBody("image/*".toMediaTypeOrNull())

        val part = MultipartBody.Part.createFormData("file", file.name, requiredBody)

        viewModel.uploadFileFromVM(part)

        inputStream?.close()
        outPutStream.close()
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            when (resultCode) {
                Activity.RESULT_OK -> {

                    imageUri = data?.data!!

                    binding.apply {
                        pIV.setImageURI(imageUri)
                        uploadBtn.visibility = View.VISIBLE
                    }
//                    binding.pIV.setImageURI(imageUri)
//
//                    binding.uploadBtn.visibility = View.VISIBLE

                }

                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun checkPermission() {

        Dexter.withContext(requireContext())
            .withPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    permissionGranted = report.areAllPermissionsGranted()

                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {


                }
            }).check()

    }


}