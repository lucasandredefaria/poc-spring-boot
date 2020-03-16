Build: mvn clean install
Project start: mvn clean spring-boot:run

#Call Endpoint

#Adiciona livro passando SKU como parametro:
Add = curl -X POST -F sku=9731880 http://localhost:8080/book/

#Exclui livro informando o SKU:
Delete = curl -X DELETE http://localhost:8080/book/123

#Pesquisa livro pelo SKU:
find = curl -X GET http://localhost:8080/book/123

#Mostra Livros com preço até R$ x:
curl -X GET "http://localhost:8080/book?limit=5&price=50"
