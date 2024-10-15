import { StatusBar, Text } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { Link } from "expo-router";

export default function App() {
  return (
    <SafeAreaView className="items-center justify-center flex-1 gap-4 bg-white">
      <Text>Welcome to</Text>
      <Text className="text-4xl font-pregular">Life Protocol!</Text>
      <Link href="/tabs" style={{ marginTop: 15 }}>
        Open Tabs
      </Link>
      <StatusBar barStyle="dark-content" />
    </SafeAreaView>
  );
}
