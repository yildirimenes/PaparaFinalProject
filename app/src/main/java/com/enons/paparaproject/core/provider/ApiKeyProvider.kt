package com.enons.paparaproject.core.provider

class ApiKeyProvider {
    companion object {
        init {
            System.loadLibrary("paparaproject")
        }
        external fun getApiKey(): String
    }
}