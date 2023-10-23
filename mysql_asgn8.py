import mysql.connector

def connect_to_mysql():
    try:
        db_connection = mysql.connector.connect(
            host="10.10.15.233",
            user="te31227",
            password="te31227",
            database="31227_db"
        )
        return db_connection
    except mysql.connector.Error as e:
        print(f"Error: {e}")
        return None

def add_record(connection, cursor):
    name = input("Enter name: ")
    age = input("Enter age: ")
    cursor.execute("INSERT INTO student (name, age) VALUES (%s, %s)", (name, age))
    connection.commit()
    print("Record added successfully!")

def delete_record(connection, cursor):
    id_to_delete = input("Enter the name to delete: ")
    cursor.execute("DELETE FROM student WHERE name = %s", (id_to_delete,))
    connection.commit()
    print("Record deleted successfully!")

def edit_record(connection, cursor):
    id_to_edit = input("Enter the name to edit: ")
    new_name = input("Enter new name: ")
    new_age = input("Enter new age: ")
    cursor.execute("UPDATE student SET name = %s, age = %s WHERE name = %s", (new_name, new_age, id_to_edit))
    connection.commit()
    print("Record edited successfully!")

def list_records(cursor):
    cursor.execute("SELECT * FROM student")
    records = cursor.fetchall()
    for record in records:
        print(f"Name: {record[0]}, Age: {record[1]}")

def main_menu():
    connection = connect_to_mysql()
    if connection is None:
        return

    cursor = connection.cursor(buffered=True)

    while True:
        print("\nMenu:")
        print("1. Add Record")
        print("2. Delete Record")
        print("3. Edit Record")
        print("4. List Records")
        print("5. Exit")
        choice = input("Enter your choice: ")

        if choice == "1":
            add_record(connection, cursor)
        elif choice == "2":
            delete_record(connection, cursor)
        elif choice == "3":
            edit_record(connection, cursor)
        elif choice == "4":
            list_records(cursor)
        elif choice == "5":
            print("Exiting the program. Goodbye!")
            connection.close()
            break
        else:
            print("Invalid choice. Please select a valid option.")

if __name__ == "__main__":
    main_menu()