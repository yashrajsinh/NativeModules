import React, { useState } from 'react';
import {
  NativeModules,
  StyleSheet,
  Platform,
  requireNativeComponent,
} from 'react-native';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';

// component
import ShowButton from './src/components/ShowButton/ShowButton';

const isAndroid = Platform.OS === 'android';

// native module
const { ImageUrlModule, DeviceModule } = NativeModules;

// native UI component
const NativeImageView = requireNativeComponent('StaticUrlImageView');

function App() {
  const [showImage, setShowImage] = useState(false);
  const [imageUrl, setImageUrl] = useState<string | null>(null);

  // call native module → get URL → show image
  const loadImage = async () => {
    try {
      const url = await ImageUrlModule.getImageURL();
      setImageUrl(url);
      setShowImage(true);
    } catch (e) {
      console.log('Error getting image URL', e);
    }
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
            isAndroid ? DeviceModule.openDeviceInfoScreen() : null
          }
          backgroundColor={isAndroid ? '#4CAF50' : '#007AFF'}
        />

        {/* Image button */}
        <ShowButton
          title="Show Image 🚀"
          onPress={loadImage}
          backgroundColor="#4CAF50"
        />

        {/* Native Image View */}
        {showImage && imageUrl && (
          <NativeImageView
            style={{ width: 300, height: 300 }}
            imageUrl={imageUrl}
          />
        )}
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
