package com.a2a.network.securityHelper


class EncryptionDecryptionHelper() {

    fun encryptData(data: String?, key: String?): String {
        var key = key
        return if (key != null && !key.isEmpty()) {

            var dataEnc = ""
            try {
                val iv = "1234567890123456"
                val _crypt =
                    CryptLib()
                dataEnc = _crypt.encrypt(data!!, key, iv) //encrypt
            } catch (e: Exception) {
                e.printStackTrace()
            }
            dataEnc
        } else
            return ""
    }


    fun decryptData(data: String?, key: String?): String {
        var key = key
        return if (key != null && !key.isEmpty()) {

            var dataDec = ""
            try {
                val iv = "1234567890123456"
                val _crypt =
                    CryptLib()
                dataDec = _crypt.decrypt(data!!, key, iv) //encrypt
            } catch (e: Exception) {
                e.printStackTrace()
            }
            dataDec
        } else throw Exception()
    }

    companion object {
        var instance: EncryptionDecryptionHelper? = null
            get() {
                if (field == null) {
                    field = EncryptionDecryptionHelper()
                }
                return field
            }
            private set
    }
}
