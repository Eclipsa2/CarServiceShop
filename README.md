# CarServiceShop
This is an OOP project in Java for cars/motorcycles repair service shop.
I have a base class -> Vehicle from which I extend two other classes: Car and Motorcycle. The vehicle class has some general attributes which are common
for both cars and motorcycles. The vehicle class includes an Employee and a Client which are both based off the class Person, that they extend. The vehicle
class also has an array of Issues which is a simple array of Issue type objects that only have 2 attributes: issue name and the issue price.

First of all you have to login:

![Screenshot 2023-03-23 at 16 12 04](https://user-images.githubusercontent.com/54707251/227230587-b11bdb8e-bd90-4305-99c6-99e24f569283.png)


In the shop you can do multiple actions:

<img width="506" alt="Screenshot 2023-04-03 at 12 59 06" src="https://user-images.githubusercontent.com/54707251/229477342-74683c45-be1f-4e1e-b61d-a181709c10d0.png">

Depending on which type of operations the user would like to do you have multiple operations. In the Hr management submenu you can see all employes, fire someone or hire one:

<img width="495" alt="Screenshot 2023-04-03 at 17 43 32" src="https://user-images.githubusercontent.com/54707251/229544322-37890c8e-f4b1-45f7-87a4-9fd3656cddd9.png">

I have used a priority queue for storing the employees. The criteria they are ordered by is each employee's salary. Whenever they are printed I use the poll method to retrieve the employees in the corect order. Example of printed employees in the right order:

<img width="531" alt="Screenshot 2023-04-03 at 18 16 27" src="https://user-images.githubusercontent.com/54707251/229553280-6153791d-f579-40f1-9120-4952a2e009b5.png">

In the client management submenu the user has multiple options:

<img width="502" alt="Screenshot 2023-04-03 at 18 18 30" src="https://user-images.githubusercontent.com/54707251/229553826-23ce099b-a94d-43e5-8fa5-0702404bfd0e.png">

Each client can have to its name multiple cars or motorcycles, or even both. When printing the clients I calculate the total repair costs for all of the clients vehicles inside the shop. If the client does not have anything left to pay to the shop it will be printed that all the repairs have been paid. Each client initialy has 0 credit to pay for the repairs but they can deposit some using option number 3 inside the submenu. The 4th option - "Pay repairs" will try to pay the repairs for a vehicle. If there are no funds available it will not be possible to do it. If the payment is successful the total of the client will be updated, deducting the successfull payment from the total.

Example of 2 clients that have not paid their repairs:

<img width="541" alt="Screenshot 2023-04-03 at 18 26 50" src="https://user-images.githubusercontent.com/54707251/229555927-1c5161bc-f9b8-4b54-b0a0-63e2215ebf69.png">

The car repairs and motorcycle repairs are similar. The only difference is the type of vehicles and motorcycles having some more attributes to their name. The change car status option will repair the car, but not automatically pay the repairs for the car. Resofting a car will just tune the vehicle and add the desired number of horsepower to the vehicle.

<img width="511" alt="Screenshot 2023-04-03 at 18 29 10" src="https://user-images.githubusercontent.com/54707251/229556570-39b5cc58-072a-45ba-8dd3-f2255051fa37.png">
