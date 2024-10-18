import React from "react";
import { FlatList, Text, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { useGlobalContext } from "../../context/GlobalProvider";

const Home = () => {
  const { user } = useGlobalContext();
  return (
    <SafeAreaView className="bg-primary p-6 text-white">
      <FlatList
        className="text-white"
        data={[
          { key: "1", title: "Item 1" },
          { key: "2", title: "Item 2" },
          { key: "3", title: "Item 3" },
          { key: "4", title: "Item 4" },
          { key: "5", title: "Item 5" },
        ]}
        renderItem={({ item }) => <Text>{item.title}</Text>}
        keyExtractor={(item) => item.key}
        ListHeaderComponent={() => (
          <View className="flex my-6 px-4 space-y-6">
            <View className="flex justify-between items-start flex-row mb-6">
              <View>
                <Text className="font-pmedium text-sm text-gray-100">
                  Welcome Back
                </Text>
                <Text className="text-2xl font-psemibold text-white">
                  {user?.username || user?.name || "User"}
                </Text>
              </View>
            </View>
          </View>
        )}
      />
    </SafeAreaView>
  );
};

export default Home;
