package com.metaplex

import com.facebook.react.bridge.WritableArray
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import com.metaplex.lib.modules.nfts.models.*

object Provider {
    fun convertNftToObject(
        nft: NFT
    ): WritableMap? {
        val map: WritableMap = WritableNativeMap()
        map.putString("name", nft.name)
        map.putString("symbol", nft.symbol)
        map.putString("uri", nft.uri)
        map.putString("mint", nft.mint.toString())
        map.putString("updateAuthority", nft.updateAuthority.toString())

        val collection: WritableMap = WritableNativeMap()
        collection.putString("key", nft.collection?.key.toString())
        nft.collection?.verified?.let { collection.putBoolean("verified", it) }
        map.putMap("collection", collection)

        map.putInt("sellerFeeBasisPoints", nft.sellerFeeBasisPoints)
        nft.editionNonce?.let { map.putInt("editionNonce", it) }

        map.putBoolean("primarySaleHappened", nft.primarySaleHappened)
        map.putBoolean("isMutable", nft.isMutable)
        map.putBoolean("primarySaleHappened", nft.primarySaleHappened)

        val masterEditionAccount: WritableMap = WritableNativeMap()
        masterEditionAccount.putString("masterEditionVersion",
            nft.masterEditionAccount?.masterEditionVersion.toString())
        map.putMap("masterEditionAccount", masterEditionAccount)

        val creators: WritableArray = WritableNativeArray()
        for(creator in nft.creators){
            val creatorMap: WritableMap = WritableNativeMap()
            creatorMap.putString("address", creator.address.toString())
            creatorMap.putInt("share", creator.share)
            creatorMap.putInt("verified", creator.verified)
            creators.pushMap(creatorMap)
        }
        map.putArray("creators", creators)

        val metadataAccount: WritableMap = WritableNativeMap()
        metadataAccount.putInt("key", nft.metadataAccount.key)
        metadataAccount.putString("mint", nft.metadataAccount.mint.toString())
        metadataAccount.putBoolean("isMutable", nft.metadataAccount.isMutable)
        metadataAccount.putString("update_authority", nft.metadataAccount.update_authority.toString())
        map.putMap("metadataAccount", metadataAccount)

        return map
    }

    fun convertMetadataToObject(
            metadata: JsonMetadata
    ) : WritableMap? {
        val map: WritableMap = WritableNativeMap()
        map.putString("name", metadata.name)
        map.putString("symbol", metadata.symbol)
        map.putString("description", metadata.description)
        metadata.seller_fee_basis_points?.let {
            map.putDouble("seller_fee_basis_points", it)
        }
        metadata.image?.let {
            map.putString("image", it)
        }
        metadata.external_url?.let {
            map.putString("external_url", it)
        }
        if (!metadata.attributes.isNullOrEmpty()) {
            val attributes: WritableArray = WritableNativeArray()
            metadata.attributes?.forEach { attribute ->
                val attributeMap: WritableMap = WritableNativeMap()
                attributeMap.putString("address", attribute.display_type)
                attributeMap.putString("share", attribute.trait_type)
                attributes.pushMap(attributeMap)
            }
            map.putArray("attributes", attributes)
        }
        return map
    }
}
