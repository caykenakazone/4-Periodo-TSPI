import { useState } from 'react';
import {
  View,
  Text,
  SafeAreaView,
  StyleSheet,
  FlatList,
  TextInput,
  Button,
  Pressable,
} from 'react-native';

export default function App() {
  const [item, setItem] = useState('');
  const [lista, setLista] = useState([]);

  const addItem = () => {
    setLista([...lista, item]);
    setItem('');
  };

  const renderItem = ({item}) =>{
    return <Text>{item}</Text>
  };
  return (
    <View style={styles.container}>
      <View style={styles.container2}>
        <TextInput
          style={styles.input}
          placeholder="Digite um item..."
          onChangeText={setItem}
          value={item}
        />
        <Pressable onPress={addItem}>
          <Text
            style={{
              textAlign: 'center',
              margin: 4,
              padding: 8,
              color: 'blue',
              fontSize: 20,
              backgroundColor: '#8b5',
              borderWidth: 2,
              borderColor : 'black'
            }}>
            Enviar
          </Text>
        </Pressable>
      </View>
      <FlatList
        style={styles.flat}
        data={lista}
        renderItem={renderItem}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    height: 200,
    flex: 1,
    justifyContent: 'center',
    backgroundColor: '#ecf0f1',
    padding: 28,
  },
  flat: {
    borderColor: 'black',
    borderWidth: 2,
    padding: 15,
    margin: 4
    
  },
  input: {
    fontSize: 20,
    borderColor: 'black',
    borderWidth: 2,
    flex: 1,
    margin: 4
  },
  container2: {
    marginTop: 50,
    marginBottom: 30,
    flexDirection: 'row',
    
  }
});
