import React, { useEffect } from 'react';
import {
  NativeModules,
  StyleSheet,
  Platform,
  Text,
  NativeEventEmitter,
} from 'react-native';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';

// component
import ShowButton from './src/components/ShowButton/ShowButton';

const isAndroid = Platform.OS === 'android';

// native module
const { NameModule, ImageUrlModule, DeviceModule } = NativeModules;
const emitter = new NativeEventEmitter(NameModule);

function App() {
  //var for name
  const [name, setName] = React.useState('');

  //useEffect to render name
  useEffect(() => {
    const sub = emitter.addListener('onNameReceived', data => {
      console.log('Received:', data);
      setName(data);
    });
    return () => sub.remove();
  }, []);

  // call native module → get URL → show image
  const loadImage = async () => {
    try {
      const url = await ImageUrlModule.openImageViewScreen(
        'https://media2.giphy.com/media/v1.Y2lkPWVjZjA1ZTQ3dDB1ZGR1cnlkdTA3cjJnM3Y4OTlnOG9jOWg4dWs2Yjl5eXp4OWc4bCZlcD12MV9naWZzX3JlbGF0ZWQmY3Q9Zw/3jcgPn9fzfaXc1EHJC/200.webp',
      );
    } catch (e) {}
  };

  return (
    <SafeAreaProvider>
      <SafeAreaView style={styles.container}>
        {/* Device button */}
        <ShowButton
          title={
            isAndroid
              ? 'Show Android Device Details 🤖'
              : 'Show iOS Device Details 📱'
          }
          onPress={() =>
            isAndroid
              ? DeviceModule.openDeviceInfoScreen()
              : NativeModules.iOSDeviceInfoModule.openDeviceScreen()
          }
          backgroundColor={isAndroid ? '#4CAF50' : '#007AFF'}
        />

        {/* Image button */}
        <ShowButton
          title="Show Image 🚀"
          onPress={() =>
            isAndroid
              ? loadImage()
              : NativeModules.NativeImageModule.showImage(
                  'https://media.tenor.com/DU-WbRilkxQAAAAM/dogevr-office.gif',
                )
          }
          backgroundColor={isAndroid ? '#4CAF50' : '#007AFF'}
        />

        {/* Name button */}
        <ShowButton
          title="Take me to the moon 🌑"
          onPress={() =>
            isAndroid
              ? NameModule.openNameScreen()
              : NativeModules.NativeImageModule.showImage(
                  'https://media.tenor.com/DU-WbRilkxQAAAAM/dogevr-office.gif',
                )
          }
          backgroundColor={isAndroid ? '#4CAF50' : '#007AFF'}
        />

        <Text style={{ marginTop: 20 }}>Name: {name}</Text>
      </SafeAreaView>
    </SafeAreaProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});

export default App;
