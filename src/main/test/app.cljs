(ns test.app
  (:require
   [shadow.react-native :refer (render-root)]
   ["react-native" :as rn]
   ["react-native-reanimated" :refer [runOnJS]]
   ["react" :as react]
   [reagent.core :as r]))

(def styles
  ^js (-> {:container
           {:flex 1
            :backgroundColor "#fff"
            :alignItems "center"
            :justifyContent "center"}
           :title
           {:fontWeight "bold"
            :textAlign "center"
            :padding 12
            :fontSize 24
            :color "blue"}
           :input {:backgroundColor "#f7f7f7"
                   :borderRadius 4
                   :padding 12}}
          (clj->js)
          (rn/StyleSheet.create)))

(defonce worker ^js (js/require "../worker/index.js"))

(defn cb [x]
  (prn "Callback to main thread " x))

(defn root []
  (r/with-let [number (r/atom 0)]
    [:> rn/View {:style (.-container styles)}
     [:> rn/Text {:style (.-title styles)} "Hello, type an int and see the magic!"]
     [:> rn/TextInput {:value (str @number)
                       :style (.-input styles)
                       :keyboard-type "numeric"
                       :on-change-text #(reset! number %)}]
     [:> rn/Pressable {:on-press #(-> (.main worker "math" (js/parseInt @number))
                                      (.then (fn [response] (prn response) (js/alert (str "Response: " (.-result response)))))
                                      (.catch (fn [err] (prn err))))}
      [:> rn/Text (str "Expensive async 2+" @number " =>")]]]))

(defn start
  {:dev/after-load true}
  []
  (render-root "clojureMultithreading" (r/as-element [root])))

(defn init []
  (start))

