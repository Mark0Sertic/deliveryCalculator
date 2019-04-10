API
-------------------
Ingridents:
-------------------

GET localhost:8080/ingredient -> retrn list of ingredients

POST localhost:8080/ingredient -> return {"id": "id", "name": "name"}
{
	"name" : "name"
}

GET localhost:8080/ingredient/id -> return {"id": "id", "name": "name"}

PUT localhost:8080/ingredient/id -> return {"id": "id", "name": "name"} -> updated ingredient

{
	"name" : "name"
}

DELETE localhost:8080/ingredient/id -> return {"id": "id", "name": "name"} -> updated ingredient

-------------------
Meals
-------------------
GET localhost:8080/meal -> retrn list of ingredients

POST localhost:8080/meal -> return {"id": "id", "name": "name", "price": 2,  "ingredients": [] - lista objekata sastojaka}
BODY:
{
	"name" : "name",
	"price" : 2
	"ingredients": [] -- list of ingredients
}

GET localhost:8080/meal/id -> return return {"id": "id", "name": "name", "price": 2,  "ingredients": [] - lista objekata sastojaka}

PUT localhost:8080/meal/id -> return return {"id": "id", "name": "name", "price": 2,  "ingredients": [] - lista objekata sastojaka} -> updated meal

Ovaj je malo tricky:
Update radi normalno ali malo se drugačije ponaša kada se pokuša updatati ingredients polje,
Svaki zamislimo da pod ingredientsima imamo tri sastojka, ako u updatu u polju ne navedemo treći
update će ga obrisati iz liste, no ako ostavimo listu praznu, neće se ništa dogoditi, ostat će sve tri.
Ideja iza ovakvog ponašanja je bila da se mogu updati ime i cije bez diranja sastojaka, kao da se satojci mogu dodavati i miciati
ali nikad da se dozvoli brisanje svih, za brisanje svih sastojaka korisnik u ovom slučaju može updatati da ostavi samo jedan
i onda apijima za birsanje pojedinog sastojka iz jela maknuti i taj (osiguranje da korisnik stvarno želi ukloniti sve sastojke iz obroka)

BODY:
{
	"name" : "name",
	"price" : 2
	"ingredients": [] -- list of ingredients
}

DELETE localhost:8080/meal/id -> return return {"id": "id", "name": "name", "price": 2,  "ingredients": [] - lista objekata sastojaka} -> updated meal

Explanation: add ingredient to meal:
PUT localhost:8080/meal/meal_id/ingredient/ingredient_id -> return return {"id": "id", "name": "name", "price": 2,  "ingredients": [] - lista objekata sastojaka} -> updated meal

Remove ingredient from meal:
DELETE localhost:8080/meal/meal_id/ingredient/ingredient_id -> return return {"id": "id", "name": "name", "price": 2,  "ingredients": [] - lista objekata sastojaka} -> updated meal

-------------------
Orders
-------------------

POST localhost:8080/order
BODY
{
	orders: {"meal_name1": quantity,
	"meal_name2": quantity,
	"meal_name3": quantity
	}
}

returns Example (for percentage price calculation):

{"meals":[{"id":1,"name":"test1","price":2.0,"ingredients":[{"id":2,"name":"testIngr2"}]},{"id":3,"name":"test2","price":2.0,"ingredients":[{"id":4,"name":"testIngr3"}]},{"id":5,"name":"test3","price":2.0,"ingredients":[{"id":4,"name":"testIngr3"},{"id":2,"name":"testIngr2"}]}],"priceWithoutDelivery":14.0,"deliveryPrice":1.4000000000000001,"total":15.4}

