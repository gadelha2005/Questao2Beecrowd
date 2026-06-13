(ns frontend.core
  (:require [frontend.api :as api])
  (:gen-class))

(defn ler-numeros [quantidade]
  (mapv (fn [_] (Double/parseDouble (read-line))) (range quantidade)))

(defn formatar-media [media]
  (format "%.1f" media))


(defn -main [& args]
  (let [lista (ler-numeros (Integer/parseInt (read-line)))
        response (api/get-positivos lista)]
    (println (:quantidade response) "valores positivos")
    (println (formatar-media (double (:media response))))))