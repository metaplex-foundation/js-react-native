package com.metaplex

import com.facebook.react.bridge.WritableArray
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import com.metaplex.lib.modules.nfts.models.NFT

object Provider {
    fun convertNftToObject(
        nft: NFT
    ): WritableMap? {
        val map: WritableMap = WritableNativeMap()
        map.putString("name", nft.name);
        map.putString("symbol", nft.symbol);
        map.putString("uri", nft.uri);
        map.putString("mint", nft.mint.toString());
        map.putString("updateAuthority", nft.updateAuthority.toString());

        val collection: WritableMap = WritableNativeMap()
        collection.putString("key", nft.collection?.key.toString())
        nft.collection?.verified?.let { collection.putBoolean("verified", it) }
        map.putMap("collection", collection);

        map.putInt("sellerFeeBasisPoints", nft.sellerFeeBasisPoints);
        nft.editionNonce?.let { map.putInt("editionNonce", it) };

        map.putBoolean("primarySaleHappened", nft.primarySaleHappened);
        map.putBoolean("isMutable", nft.isMutable);
        map.putBoolean("primarySaleHappened", nft.primarySaleHappened);

        val masterEditionAccount: WritableMap = WritableNativeMap()
        masterEditionAccount.putString("masterEditionVersion",
            nft.masterEditionAccount?.masterEditionVersion.toString())
        map.putMap("masterEditionAccount", masterEditionAccount);

        val creators: WritableArray = WritableNativeArray()
        for(creator in nft.creators){
            val creatorMap: WritableMap = WritableNativeMap()
            creatorMap.putString("address", creator.address.toString());
            creatorMap.putInt("share", creator.share);
            creatorMap.putInt("verified", creator.verified);
            creators.pushMap(creatorMap);
        }
        map.putArray("creators", creators)

        val metadataAccount: WritableMap = WritableNativeMap()
        metadataAccount.putInt("key", nft.metadataAccount.key);
        metadataAccount.putString("mint", nft.metadataAccount.mint.toString());
        metadataAccount.putBoolean("isMutable", nft.metadataAccount.isMutable);
        metadataAccount.putString("update_authority", nft.metadataAccount.update_authority.toString());
        map.putMap("metadataAccount", metadataAccount)

        return map
    }
}
