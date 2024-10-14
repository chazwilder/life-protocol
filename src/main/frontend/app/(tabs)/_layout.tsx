import { Slot, Stack, SplashScreen } from 'expo-router'
import { Text } from 'react-native'
import { useFonts } from 'expo-font'
import { useEffect } from 'react'

SplashScreen.preventAutoHideAsync();

const RootLayout = () => {
  let fontsLoaded = useFonts({
    'Poppins-Regular': require('../../assets/fonts/Poppins-Regular.ttf'),
    'Poppins-Bold': require('../../assets/fonts/Poppins-Bold.ttf'),
    'Poppins-SemiBold': require('../../assets/fonts/Poppins-SemiBold.ttf'),
    'Poppins_Black': require('../../assets/fonts/Poppins-Black.ttf'),
  });

  useEffect(() => {
    if (fontsLoaded) SplashScreen.hideAsync();
  },[fontsLoaded]);

  return(
    <Stack>
      <Stack.Screen name='index' options={{headerShown: false}} />
    </Stack>
    )
}

export default RootLayout

