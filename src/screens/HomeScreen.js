import React from 'react';
import { SafeAreaView, View, FlatList, StyleSheet, Text, StatusBar, Image } from 'react-native';
import logo from '../assets/logo.png';

const DATA = [
  {
    id: '1',
    title: 'First Item',
    address:'1234',
    image: 'https://via.placeholder.com/200x200',
  },
  {
    id: '2',
    title: 'Second Item',
    address:'1234',
    image: 'https://via.placeholder.com/200x200',
  },
  {
    id: '3',
    title: 'Third Item',
    address:'1234',
    image: 'https://via.placeholder.com/200x200',
  },
  {
    id: '4',
    title: 'Fouth Item',
    address:'1234',
    image: 'https://via.placeholder.com/200x200',
  },
  {
    id: '5',
    title: 'Fifth Item',
    address:'1234',
    image: 'https://via.placeholder.com/200x200',
  },
  {
    id: '6',
    title: 'Sixth Item',
    address:'1234',
    image: 'https://via.placeholder.com/200x200',
  },
];

const Item = ({ item }) => {
  return (
    <View style={styles.item}>
      <Image style={{width: '100%', height: 200}} source={{uri:item.image}} />
      <Text style={styles.title}>{item.title}</Text>
      <Text style={styles.publicKey}>{item.address}</Text>
    </View>
  );
}

const HomeScreen = () => {

  const renderItem = ({ item }) => (
    <Item item={item} />
  );

  return (
    <SafeAreaView style={styles.container}>
    <Image style={styles.logo} source={logo} />
      <FlatList
        key={'nftList'}
        numColumns={2}
        data={DATA}
        renderItem={renderItem}
        keyExtractor={item => item.id}
      />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  logo: {
    width: 70,
    height: 50
  },
  container: {
    flex: 1,
    marginTop: 0,
    backgroundColor: '#000'
  },
  item: {
    width: '50%',
    backgroundColor: '#292929',
    padding: 20,
    marginVertical: 8,
    marginHorizontal: 1,
    maxHeight: 350,
    overflow: 'hidden'
  },
  title: {
    fontSize: 18,
  },
  publicKey: {
    fontSize: 12,
  }
});

export default HomeScreen;
