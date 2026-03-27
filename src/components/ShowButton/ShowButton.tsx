import React from 'react';
import {
  TouchableOpacity,
  Text,
  StyleSheet,
  View,
  GestureResponderEvent,
} from 'react-native';

type ShowButtonProps = {
  title: string;
  onPress: (event: GestureResponderEvent) => void;
  backgroundColor?: string; // customizable background
  textColor?: string; // customizable text color
  style?: object; // extra style overrides
};

const ShowButton: React.FC<ShowButtonProps> = ({
  title,
  onPress,
  backgroundColor = '#4CAF50', // default green
  textColor = '#fff', // default white text
  style = {},
}) => {
  return (
    <View style={{ marginVertical: 6 }}>
      <TouchableOpacity
        style={[styles.button, { backgroundColor }, style]}
        activeOpacity={0.7}
        onPress={onPress}
      >
        <Text style={[styles.text, { color: textColor }]}>{title}</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  button: {
    paddingVertical: 14,
    paddingHorizontal: 20,
    borderRadius: 10,
    alignItems: 'center',
    justifyContent: 'center',
    elevation: 5, // Android shadow
  },
  text: {
    fontSize: 16,
    fontWeight: '600',
  },
});

export default ShowButton;
