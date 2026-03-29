/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import { NativeModules, StyleSheet, Platform } from 'react-native';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';

//component
import ShowButton from './src/components/ShowButton/ShowButton';
const isAndroid = Platform.OS === 'android';
function App() {
  return (
    <SafeAreaProvider>
      <SafeAreaView style={styles.container}>
        <ShowButton
          title={
            isAndroid
              ? 'Show Android Device Details 🤖'
              : 'Show iOS Device Details 📱'
          }
          onPress={() =>
            isAndroid
              ? NativeModules.DeviceModule.openDeviceInfoScreen()
              : NativeModules.iOSDeviceInfoModule.openDeviceScreen()
          }
          backgroundColor={isAndroid ? '#4CAF50' : '#007AFF'}
        />
      </SafeAreaView>
    </SafeAreaProvider>
  );
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  button: {
    backgroundColor: '#4CAF50', // nice green
    paddingVertical: 20,
    paddingHorizontal: 20,
    borderRadius: 10,
    alignItems: 'center',
    justifyContent: 'center',
    elevation: 5, // Android shadow
  },
  text: {
    color: '#fff',
    fontSize: 16,
    fontWeight: '600',
  },
});

export default App;
