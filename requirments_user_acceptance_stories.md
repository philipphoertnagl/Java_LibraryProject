Java Library Project:

**<u>Functional Requirements:</u>**
1. There should be a user interface (maybe only command line interface)
2. The user of the program should be able to add new Book Titles to the Library system.
3. The user of the programm should be able to add and update the amount of copies of one Book title in the Library.
4. The user of the programm should be able to add new Clients (Users) to the Library system.
5. The user of the programm should be able to assign some lendings of books to specific users.
6. The Library system should automatically set the due time when a book is borrowed.
7. The Library system should inform when a Client has borrowed a book for too long and the due time is over.
8. The Program should save and store all data (borrowed books, due times, Client info etc) to an external .txt file and also load from this file with every programm start.
9. The program should be able to display all current users with borrowed books.
10. The system should allow searching for books by title, author, or ISBN.
11. The system should generate reports like: number of books in the library, number of books currently loaned out, number of registered users, and so on.




**User Stories:**
1. As a Library employee, I want to be able to add new Clients to the Library system.
2. As a Library employee, I want to be able to add new Book Titles to the Library system.
3. As a Library employee, I want to be able to add new copies of Book titles to the Libary system.
4. As a Library employee, I want to be able to remove Clients from the system.
5. As a Library employee, I want to be able to remove Book titles and copies of books in the Library system.


Acceptance Stories:
1.
Given: A Client wants to borrow a copy of a book.

When: A specific book is chosen.

Then: The due time of returning the book is set and the borrowed book is assigned to the client.

2.
Given: A client returns the borrowed book.

When: The book copy is added to the library system.

Then: The due time is set 0 and the availability of this copy is set to 1.
