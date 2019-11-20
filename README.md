# auctionar
Web based application to manage auction.

AUCTIONAR Documentation

This is a quick documentation of the application AUCTIONAR.
AUCTIONAR is an application that manages auctions by offering the possibility to create action houses with defined auctions. The users can bid running auctions by proposing bigger price than current auction price.
https://www.getpostman.com/collections/2dcb85a588547faec129 (POSTMAN).

1.	Functionalities by endpoint

a.	Create new Auction House with given name
URL: http://localhost:8080/auctionhouses/
Http method: POST
Requirements: Submit JSON with “name” field. (NOT NULL)
{
    "name" : "auction maison"
}

b.	Get All existing auction houses
URL: http://localhost:8080/auctionhouses/
Http method: GET
c.	Delete an existing auction houses
URL: http://localhost:8080/auctionhouses/{id}
Http method: DELETE
Requirement: id must correspond to existing auction house ID.
d.	Create Auction with parameters
URL: http://localhost:8080/auctions/
Http method: POST
Requirement: Submit correct JSON with required data.
 

{
    "name" : "Peugeot 3008 SUV 2",
    "description" : "voiture de high class",
    "startTime": "01-11-2019 00:00:00",
    "endTime": "23-11-2019 17:35:00",
    "startPrice": 17500,
    "currentPrice": 17500,
    "idAuctionHouse": 1
}

e.	Bid started auction
URL: http://localhost:8080/biddings/
Http method: POST
Requirement: Submit JSON with correct information.
{
    "bidderName": "mamadou",
    "idAuction": 2,
    "price": 19500
}

f.	Get Auction details
URL (General details): http://localhost:8080/auctions/{id} GET
URL (only biddings): http://localhost:8080/auctions/{id}/biddings GET
URL (winner): http://localhost:8080/auctions/{id}/winner GET

g.	Delete existing auction (with all biddings)
URL: http://localhost:8080/auctions/{id}
Http method: DELETE

Be careful, exception are thrown if data structure and constraints are not respected.



