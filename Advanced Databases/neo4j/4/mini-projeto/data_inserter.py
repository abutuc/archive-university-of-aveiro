from neo4j import GraphDatabase
import shutil


def insert_books_authors_publishers_and_relations(tx, csv_file):
    tx.run("""
        LOAD CSV WITH HEADERS FROM '""" + csv_file + """' AS row 
        MERGE (b:Book {title: row.title, rating: toFloat(row.rating), num_pages: toInteger(row.num_pages), publish_date: datetime(row.publish_date)})
        MERGE (a:Author {name: row.author})
        MERGE (p:Publisher {name: row.publisher})
        MERGE (a)-[w:WROTE]-(b)
        MERGE (p)-[pub:PUBLISHED]-(b);""")


def insert_users(tx, csv_file):
    tx.run("""
            LOAD CSV WITH HEADERS FROM '""" + csv_file + """' AS row 
            MERGE (u:User {id: toInteger(row.userId), name: row.name});""")


def insert_relation_user_user(tx, csv_file):
    tx.run("""
                LOAD CSV WITH HEADERS FROM '""" + csv_file + """' AS row 
                MERGE (u1:User {id: toInteger(row.userId1), name: row.user1})
                MERGE (u2:User {id: toInteger(row.userId2), name: row.user2})
                MERGE (u1)-[f:FOLLOWS]-(u2);""")


def insert_relation_user_author(tx, csv_file):
    tx.run("""
                LOAD CSV WITH HEADERS FROM '""" + csv_file + """' AS row
                MERGE (u:User {id: toInteger(row.userId), name: row.user})
                MERGE (a:Author {name: row.author_name})
                MERGE (u)-[l:LIKES]-(a);""")


def insert_relation_user_books(tx, csv_file):
    tx.run("""
            LOAD CSV WITH HEADERS FROM '""" + csv_file + """' AS row
            MERGE (u:User {id: toInteger(row.userId), name: row.user})
            MERGE (b:Book {title: row.book_name, rating: toFloat(row.book_rating), num_pages: toInteger(row.book_num_pages), publish_date: datetime(row.book_publish_date)})
            MERGE (u)-[l:LIKES]-(b);""")


def move_files_to_neo4j(files, dest):
    for file in files:
        try:
            shutil.copy(file, dest)
        except FileExistsError:
            print("File {} already exits in destination folder.".format(file))


def dump_database(tx):
    tx.run("MATCH (n) DETACH DELETE n")


def exec_write(func, param=None):
    with driver.session(database="books") as session:
        if param is None:
            session.execute_write(func)
        else:
            session.execute_write(func, param)


if __name__ == '__main__':
    uri = "bolt://localhost:7687"
    driver = GraphDatabase.driver(uri)

    csv_files = ['data/books.csv', 'data/users.csv', 'data/users_authors.csv', 'data/users_books.csv', 'data/users_users.csv']

    # update to your local neo4j import data folder
    destination = "/Users/andre_butuc/Library/Application Support/Neo4j Desktop/Application/relate-data/dbmss/dbms-667c0f95-e7bc-47a0-b1f8-4a59fee62687/import/"

    # copy csv files from data to neo4j import folder
    move_files_to_neo4j(csv_files, destination)

    # after copying files
    neo4j_csv_files = ['file:///books.csv', 'file:///users.csv', 'file:///users_authors.csv', 'file:///users_books.csv', 'file:///users_users.csv']

    # To Dump DataBase
    exec_write(dump_database)
    # Insertion of Books, Authors, Publishers and the Relationships between them
    exec_write(insert_books_authors_publishers_and_relations, neo4j_csv_files[0])
    # To Insert Users
    exec_write(insert_users, neo4j_csv_files[1])
    # To Insert Users-Users Relationship
    exec_write(insert_relation_user_user, neo4j_csv_files[4])
    # To Insert Users-Authors Relationship
    exec_write(insert_relation_user_author, neo4j_csv_files[2])
    # To Insert Users-Books Relationship
    exec_write(insert_relation_user_books, neo4j_csv_files[3])


