import {NativeModules} from 'react-native';
const {Metaplex} = NativeModules;

export const findByMint = () => {
  // devnet/mainnet/testnet | default -> mainnet
  const environment = 'devnet';

  const ownerPublicKey = 'EAqjUWVX2m9fdfGNBzTY5zSiid1Sb9V3x6EL8ssZBTkw';
  const mintKey = 'HfUXV9jP7qwMBKSvyoQDYEyZpPVSfteysS62DBpGNSqz';

  // initializes the metaplex object for given values
  Metaplex.create(environment, ownerPublicKey);

  // find nft metadata for given mint key
  Metaplex.findByMint(mintKey, (data, error) => console.log(data, error));
};
