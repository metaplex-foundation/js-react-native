import {NativeModules} from 'react-native';
const {Metaplex} = NativeModules;

class MetaplexBridge {
  constructor(environment, ownerPublicKey) {
    // environment -> devnet/mainnet/testnet | default -> mainnet
    // ownerPublicKey -> public key of owner wallet
    Metaplex.create(environment, ownerPublicKey);
  }

  findByMint(mintKey, callback) {
    // find nft metadata for given mint key
    // callback function returns the data returned
    Metaplex.findByMint(mintKey, (data, error) => callback(data, error));
  }
}

export default MetaplexBridge;
