package com.nimons360.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nimons360.Nimons360App
import com.nimons360.data.remote.dto.LoginRequest
import com.nimons360.databinding.FragmentLoginBinding
import com.nimons360.ui.MainActivity
import com.nimons360.util.NetworkResult
import com.nimons360.util.handleApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun performLogin(email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) {
                val apiService = Nimons360App.instance.apiClient.apiService
                handleApiResponse {
                    apiService.login(LoginRequest(email, password))
                }
            }

            when (result) {
                is NetworkResult.Success -> {
                    val data = result.data.data
                    if (data != null) {
                        Nimons360App.instance.sessionManager.apply {
                            saveToken(data.token)
                            saveTokenExpiry(data.expiresAt)
                            saveUserInfo(
                                id = data.user.id,
                                nim = data.user.nim,
                                email = data.user.email,
                                name = data.user.fullName
                            )
                        }
                        navigateToMain()
                    }
                }
                is NetworkResult.Error -> {}
                is NetworkResult.Loading -> {}
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
