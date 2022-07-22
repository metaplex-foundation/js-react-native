package com.metaplex

import com.facebook.react.bridge.*
import com.metaplex.lib.Metaplex
import com.metaplex.lib.drivers.indenty.ReadOnlyIdentityDriver
import com.metaplex.lib.drivers.storage.OkHttpSharedStorageDriver
import com.metaplex.lib.solana.SolanaConnectionDriver
import com.solana.core.PublicKey
import com.solana.networking.RPCEndpoint

class Metaplex(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private var metaplex: Metaplex? = null

    override fun getName() = "Metaplex"

    @ReactMethod(isBlockingSynchronousMethod = true)
    fun create (environment: String, ownerKey: String) {
        val ownerPublicKey = PublicKey(ownerKey)
        val endpoint = when (environment) {
            "devnet" -> {
                RPCEndpoint.devnetSolana;
            }
            "testnet" -> {
                RPCEndpoint.testnetSolana;
            }
            else -> {
                RPCEndpoint.mainnetBetaSolana;
            }
        }
        val solanaConnection = SolanaConnectionDriver(endpoint)
        val solanaIdentityDriver = ReadOnlyIdentityDriver(ownerPublicKey, solanaConnection.solanaRPC)
        val storageDriver = OkHttpSharedStorageDriver()
        metaplex = Metaplex(solanaConnection, solanaIdentityDriver, storageDriver)
    }

    @ReactMethod
    fun findByMint(mintKey: String, callback: Callback) {
        if(metaplex == null) {
            callback.invoke(null, "create method is not called before")
            return
        }
        metaplex?.nft?.findByMint(PublicKey(mintKey)) {
            it.onSuccess {
                metaplex?.let { it1 ->
                    it.metadata(it1) { result ->
                        result.onSuccess {
                            callback.invoke(it.toString(), null)
                        }.onFailure {
                            callback.invoke(null, it.message)
                        }
                    }
                }
            }.onFailure {
                callback.invoke(null, it.message)
            }
        }
    }
}