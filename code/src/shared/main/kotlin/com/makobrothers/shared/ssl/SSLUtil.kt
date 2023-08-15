package com.makobrothers.shared.ssl

import javax.net.ssl.*
import java.security.*
import java.security.cert.X509Certificate

class SSLUtil private constructor() {

    init {
        throw UnsupportedOperationException("Do not instantiate libraries.")
    }

    companion object {
        private val UNQUESTIONING_TRUST_MANAGER = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate>? = null
            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
        })

        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        fun turnOffSslChecking() {
            val sc = SSLContext.getInstance("SSL")
            sc.init(null, UNQUESTIONING_TRUST_MANAGER, null)
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
        }

        @Throws(KeyManagementException::class, NoSuchAlgorithmException::class)
        fun turnOnSslChecking() {
            SSLContext.getInstance("SSL").init(null, null, null)
        }
    }

}