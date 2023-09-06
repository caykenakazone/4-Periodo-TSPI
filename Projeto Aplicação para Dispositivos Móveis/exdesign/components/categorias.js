import React from 'react';
import { Text, SafeAreaView, StyleSheet, View, Image, FlatList } from 'react-native';

import img1 from '../assets/01-tablelamps.png';
import img2 from '../assets/02-ceilinglamps.png';
import img3 from '../assets/03-sconces.png';
import img4 from '../assets/04-floorlamps.png';
import img5 from '../assets/05-lightdecor.png';
import img6 from '../assets/06-garlands.png';

const data = [
  { image: img1, title: 'Abajur' },
  { image: img2, title: 'Lâmpada de teto' },
  { image: img3, title: 'Arandela' },
  { image: img4, title: 'Luminária de chão' },
  { image: img5, title: 'Luz de decoração' },
  { image: img6, title: 'Poste de iluminação' },
];

export default function Categorias() {
  const renderItem = ({ item }) => (
    <View style={styles.containerImg}>
      <Image style={styles.imagem} source={item.image} resizeMode="contain" />
      <Text>{item.title}</Text>
    </View>
  );

  return (
    <FlatList
      data={data}
      renderItem={renderItem}
      numColumns = {2}
      contentContainerStyle={styles.container}
    />
  );
}

const styles = StyleSheet.create({
  imagem: {
    width: 120,
    height: 120,
    margin: 20,
  },
  container: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  containerImg: {
    alignItems: 'center',
    margin: 5
  },
});
