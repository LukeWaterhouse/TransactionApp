# Assessment 3 Team Project - Small Business Case Study 

- Run the Main class in order to use the program
- This will open a user interface in the console where you can manipulate the Hibernate database by adding items, updating them 
and performing transactions
- Also has options for displaying the database and getting information about certain stock items

This is what comes up if you run Main:
 ```
      1 - Display current stock table
      2 - Get record of item by a given id
      3 - Add a new item to stock table
      4 - Update stock of an item
      5 - Perform a transaction
      
      >> 
   ```
   
   
   1 - This will output an ASCII table of the Stock table with information about each item
   
   2 - This will output an ASCII table of a certain Stock item determined by the user input of its ID
   
   3 - This will create a new item to add to the Stock table with custom fields determined by the user input
   
   4 - This will allow the user to add or delete stock from a certain Stock item determined by its ID. If all of the stock
   is removed it will delete this item from the table.
   
   5 - This will initiate a transaction allowing the user to add as many items as they want to their shopping cart, before telling them the price and asking how much money 
   they will give. It then calculates the amount of change that should be given and uses this information to add a transaction to the Transactions table and STOCK_TRANSACTION join table. 
   A receipt will then be printed showing this information to the user.
   
   
   
  
