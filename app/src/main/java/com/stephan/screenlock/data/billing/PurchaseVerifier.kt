package com.stephan.screenlock.data.billing

import android.util.Base64
import java.security.KeyFactory
import java.security.PublicKey
import java.security.Signature
import java.security.spec.X509EncodedKeySpec

/**
 * Clientseitige RSA/SHA1withRSA-Signaturpruefung (klassisches Play
 * "Security.java"-Verfahren). Fail-closed: ohne gesetzten Key schlaegt jede
 * Verifikation bewusst fehl. Siehe claude/project-setup.md — nur
 * clientseitige Ebene, keine serverseitige Zweitverifikation.
 */
object PurchaseVerifier {

    // TODO: aus Play Console -> Monetization setup -> Licensing eintragen.
    private const val BASE64_PUBLIC_KEY = ""

    fun verify(signedData: String, signature: String): Boolean {
        if (BASE64_PUBLIC_KEY.isBlank()) return false
        return try {
            val publicKey = generatePublicKey(BASE64_PUBLIC_KEY)
            val sig = Signature.getInstance("SHA1withRSA")
            sig.initVerify(publicKey)
            sig.update(signedData.toByteArray())
            sig.verify(Base64.decode(signature, Base64.DEFAULT))
        } catch (e: Exception) {
            false
        }
    }

    private fun generatePublicKey(base64Key: String): PublicKey {
        val keyBytes = Base64.decode(base64Key, Base64.DEFAULT)
        return KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(keyBytes))
    }
}
