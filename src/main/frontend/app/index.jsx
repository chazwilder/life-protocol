import { Link, router } from "expo-router";
import { ScrollView, StatusBar, Text, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import CustomButton from "../components/CustomButton";

export default function App() {
  return (
    <SafeAreaView className="h-full bg-primary">
      <ScrollView
        contentContainerStyle={{ height: "100%", alignItems: "center" }}
      >
        <View className="items-center justify-center w-4/5 h-full min-h-[85vh] gap-4">
          <Text className="text-3xl text-center text-white font-pregular">
            Architect Your Life, Master Your Destiny
          </Text>
          <Text className="text-4xl font-pblack text-secondary-200">
            Life Protocol!
          </Text>
          <Link href="/(tabs)/home" style={{ marginTop: 15, color: "#FFF" }}>
            Open Tabs
          </Link>
          <CustomButton
            title="Continue with Email"
            handlePress={() => router.push("/sign-in")}
            containerStyles="w-full mt-7"
          />
        </View>
      </ScrollView>
      <StatusBar backgroundColor="#161622" style="light" />
    </SafeAreaView>
  );
}
