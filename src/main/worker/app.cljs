(ns worker.app)

(defn ^:export init []
  (prn "I RUN IN WORKLET!"))

(defn- clojure-code []
  (merge {:a 1} {:b 2}))

(defn ^:export math [cb]
  (prn "I DO EXPENSIVE CALL IN WORKLET")
  #js {:result (+ 2 2)
       :hello (clj->js (clojure-code))})
