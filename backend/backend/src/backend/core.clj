(ns backend.core
  (:require [backend.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn -main [& args]
  (println "Servidor iniciando na porta 3007")
  (run-jetty app {:port 3007 :join? true}))


