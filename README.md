# Metaplex React Native

Provides a bridge library of ios and android metaplex native sdks.

⚠️ Please note that this SDK has been implemented from scratch and is currently in alpha. This means some of the core API and interfaces might change from one version to another. Feel free to contact me about bugs, improvements and new use cases.

Currently bridge for following methods from Android SDK have been implemented:

   - `findByMint`

## Usage

1. **Clone the repository**

   ```sh
   git clone https://github.com/metaplex-foundation/js-react-native
   ```

2. **Import Metaplex Native Module from NativeModules**

   ```js
   import {NativeModules} from 'react-native';
   const {Metaplex} = NativeModules;
   ```

3. **Initialize Metaplex by calling the create method**

    `create` method takes two arguments :
    - `environment` : It can take three values mainnet/testnet/devnet . Default value is mainnet
    - `ownerPublicKey` : It is the public key of owner wallet

    ```js
    Metaplex.create(environment, ownerPublicKey);
    ```
    All the methods can only be used after calling `create` method.

## Methods

Check out [`Metaplex.js`](./Metaplex.js) file for code samples of following methods

1. **findByMint**

    findByMint method takes two arguments :
    - `mintKey` : It is the mint key for which nft data is to be found
    - `callback` : Callback function returns nft `data` and `error` if there is any

    ```js
    Metaplex.findByMint(mintKey, (data, error) => console.log(data, error));
    ```

## Next Steps

As mentioned above, this SDK is still in very early stages. We plan to bridge the complete mobile SDK of Metaplex. Here’s a quick overview of what we plan to work on next.

- Bridge existing functionalities from android and ios native sdk

