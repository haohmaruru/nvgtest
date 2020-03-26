package com.example.nvgtest.util

import com.squareup.okhttp.Protocol
import okhttp3.OkHttpClient

import java.security.cert.CertificateException
import java.util.Arrays

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


/**
 * Created by norman on 2/23/15.
 */
class UnsafeOkHttpClient {
    companion object {
        fun getUnsafeOkHttpClient(): com.squareup.okhttp.OkHttpClient {
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate>? {
                        return null
                    }
                })

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory

                val okHttpClient = com.squareup.okhttp.OkHttpClient()
                okHttpClient.sslSocketFactory = sslSocketFactory
                okHttpClient.protocols = Arrays.asList(Protocol.HTTP_1_1)
                okHttpClient.hostnameVerifier = HostnameVerifier { hostname, session -> true }

                return okHttpClient
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }
    }

    // Create a trust manager that does not validate certificate chains
    // Install the all-trusting trust manager
    // Create an ssl socket factory with our all-trusting manager

}
