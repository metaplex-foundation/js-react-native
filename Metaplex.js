import {NativeModules} from 'react-native';
const {Metaplex} = NativeModules;

class MetaplexBridge {
  constructor(environment, ownerPublicKey) {
    // environment -> devnet/mainnet/testnet | default -> mainnet
    // ownerPublicKey -> public key of owner wallet
    Metaplex.create(environment, ownerPublicKey);
  }

  findByMint(mintKey) {
    // find nft metadata for given mint key
    // callback function returns the data returned
    const promise = new Promise((resolve, reject) => {
      Metaplex.findByMint(mintKey, (data, error) => {
        if (error) {
          reject(error);
        }
        resolve(data);
      });
    });

    return promise;
  }

  findAllByOwner(ownerPublicKey, callback) {
    // find all nfts by a given owner public key
    Metaplex.findAllByOwner(ownerPublicKey, (data, error) =>
      callback(data, error),
    );
  }
}

export default MetaplexBridge;
