import { StatusBar, Text } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export default function App() {
  return (
    <SafeAreaView className="flex-1 items-center justify-center bg-white">
      <Text>Welcome to</Text>
      <Text className="text-3xl font-pblack">Life Protocol!</Text>
      <StatusBar barStyle="dark-content" />
    </SafeAreaView>
   
  );
}