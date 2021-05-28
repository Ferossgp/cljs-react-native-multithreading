(ns test.app
  (:require
    [shadow.react-native :refer (render-root)]
    ["react-native" :as rn]
    ["react-native-reanimated" :refer [runOnJS]]
    ["react" :as react]
    [reagent.core :as r]
))

(def styles
  ^js (-> {:container
           {:flex 1
            :backgroundColor "#fff"
            :alignItems "center"
            :justifyContent "center"}
           :title
           {:fontWeight "bold"
            :fontSize 24
            :color "blue"}}
          (clj->js)
          (rn/StyleSheet.create)))

(defonce worker ^js (js/require "../worker/index.js"))

(defn cb [x]
  (prn "Callback to main thread " x))

(defn root []
  [:> rn/View {:style (.-container styles)}
   [:> rn/Text {:style (.-title styles)} "Hello!"]
   [:> rn/Pressable {:on-press #(do (-> (.main worker "math" nil)
                                        (.then (fn [response] (prn response) (js/alert (str "Response: " (.-result response)))))
                                        (.catch (fn [err] (prn err)))))}
    [:> rn/Text "Expensive async 2+2 =>"]]
   ])

(defn start
  {:dev/after-load true}
  []
  (render-root "clojureMultithreading" (r/as-element [root])))

(defn init []
  (start))

