(ns backend.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [cheshire.core :refer [generate-string]]
            [backend.domain :as domain]
            [clojure.string :as str]
            ))

(defn como-json [conteudo & [status]]
  {:status (or status 200)
   :headers {"Contenty-Type" "application/json; charset=utf-8"}
   :body (generate-string conteudo)})

(defroutes app-routes
  (GET "/positivos" {params :params}
    (let [raw (get params :numeros)]
      (if (nil? raw)
        (como-json {:erro "Parâmetro numeros é obrigatório"} 400)
        (let [numeros (->> (str/split raw #",")
                           (mapv (fn [s] (Double/parseDouble s))))
              quantidade (domain/quantidade-numeros-positivos numeros)
              media (domain/media-numeros-positivos numeros)]
          (como-json {:quantidade quantidade
                      :media media})))))
  (route/not-found (como-json {:mensagem "Recurso não encontrado"} 404)))

(def app
  (-> app-routes
      (wrap-defaults api-defaults)
      (wrap-json-body {:keywords? true :bigdecimals? true})))



