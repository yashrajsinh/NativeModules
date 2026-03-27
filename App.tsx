/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import {
  NativeModules,
  StyleSheet,
  Text,
  TouchableOpacity,
} from 'react-native';
import { SafeAreaProvider, SafeAreaView } from 'react-native-safe-area-context';
//custom toast
import Toast from 'react-native-toast-message';
function App() {
  const handleAndroid = () => {
    NativeModules.DeviceModule.openDeviceInfoScreen();
  };
  return (
    <SafeAreaProvider>
      <SafeAreaView style={styles.container}>
        <TouchableOpacity
          style={styles.button}
          activeOpacity={0.4}
          onPress={() => handleAndroid()}
        >
          <Text style={styles.text}> Show Android Device Details 🤖 </Text>
        </TouchableOpacity>
        <Toast />
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
    paddingVertical: 14,
    paddingHorizontal: 20,
    borderRadius: 10,
    alignItems: 'center',
    justifyContent: 'center',
    elevation: 5, // Android shadow
    marginHorizontal: 20, // adds space left & right
  },
  text: {
    color: '#fff',
    fontSize: 16,
    fontWeight: '600',
  },
});

export default App;
