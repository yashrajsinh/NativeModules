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

  //show name
  useEffect(() => {
    const subscription = emitter.addListener('onNameSelected', name => {
      console.log('Received:', name);
      setName(name);
    });

    return () => subscription.remove();
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
          onPress={async () => {
            if (isAndroid) {
              try {
                const receivedName = await NameModule.openNameScreen();
                setName(receivedName);
              } catch (e) {
                // User dismissed or cancelled — do nothing
              }
            } else {
              NameModule.openNameScreen();
            }
          }}
          backgroundColor={isAndroid ? '#4CAF50' : '#007AFF'}
        />

        <Text style={styles.nameCard}>{name ? `Name: ${name}` : null}</Text>
      </SafeAreaView>
    </SafeAreaProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  label: {
    marginTop: 20,
    fontSize: 14,
    color: '#777',
  },

  nameCard: {
    marginTop: 8,
    fontSize: 20,
    fontWeight: '600',
    color: '#000',
    backgroundColor: '#f2f2f2',
    paddingVertical: 12,
    paddingHorizontal: 16,
    borderRadius: 10,
    overflow: 'hidden',
  },
});

export default App;
