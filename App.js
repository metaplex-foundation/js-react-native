/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import RootNavigator from './src/navigation/RootNavigator';
import Metaplex from './Metaplex';

const metaplex = new Metaplex(
  'devnet',
  'EAqjUWVX2m9fdfGNBzTY5zSiid1Sb9V3x6EL8ssZBTkw',
);

metaplex
  .findByMint('HfUXV9jP7qwMBKSvyoQDYEyZpPVSfteysS62DBpGNSqz')
  .then(data => console.log('findByMint', data))
  .catch(error => console.log(error));

metaplex
  .metadata('HfUXV9jP7qwMBKSvyoQDYEyZpPVSfteysS62DBpGNSqz')
  .then(data => console.log('metadata', data))
  .catch(error => console.log(error));

metaplex
  .findAllByOwner('EAqjUWVX2m9fdfGNBzTY5zSiid1Sb9V3x6EL8ssZBTkw')
  .then(data => console.log('findAllByOwner', data))
  .catch(error => console.log(error));

const App = () => {
  return <RootNavigator />;
};

export default App;
