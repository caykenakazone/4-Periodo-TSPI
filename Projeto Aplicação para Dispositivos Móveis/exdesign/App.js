import { Text, SafeAreaView, StyleSheet, View, Image } from 'react-native';

import icon from './assets/icone-sacola.png';

import Categorias from './components/categorias';

export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.containerM}>
        <View style={styles.startContainer}>
          <Text style={{ fontSize: 20 }}>Lighteria</Text>
        </View>

        <View style={styles.endContainer}>
          <Image
            style={{
              width: 30,
              height: 30,
            }}
            source={icon}
            resizeMode="contain"
          />
        </View>
      </View>

      <View style={styles.separator}>
        <View style={styles.line} />
        <Text style={{ paddingHorizontal: 10 }}>Categorias</Text>
        <View style={styles.line} />
      </View>

      <View>
        <Categorias />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {  
   paddingHorizontal: 20,
   paddingVertical: 70

  },
  containerM: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  startContainer: {

    alignItems: 'flex-start',
  },
  endContainer: {

    alignItems: 'flex-end',
  },  separator: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 10,
  },
  line: {
    flex: 1,
    height: 1,
    backgroundColor: 'black',
  },  separator: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 10,
  },
  line: {
    flex: 1,
    height: 1,
    backgroundColor: 'black',
  },
});
