from neo4j import GraphDatabase


def query1(tx):
    """ List all books written by J.R.R. Tolkien."""

    return tx.run("""
            MATCH (a:Author {name: 'J.R.R. Tolkien'})-[w:WROTE]->(b:Book) return b.title;
    """).values()


def query2(tx):
    """ List all books published by Penguin Books."""
    return tx.run("""
            MATCH (p:Publisher {name: 'Penguin Books'})-[pub:PUBLISHED]->(b:Book) return b.title;
        """).values()


def query3(tx):
    """ List all books that were liked by the user with id equal to 15."""
    return tx.run("""
            MATCH (u: User {id: 15})-[:LIKES]-(b:Book) return b.title;
        """).values()


def query4(tx):
    """ List the top 10 of books with most pages."""
    return tx.run("""
            MATCH (b:Book) return b.title, b.num_pages order by b.num_pages desc limit 10;
        """).values()


def query5(tx):
    """ List the users that follow each other mutually."""
    return tx.run("""
            MATCH (u1: User)-[f1:FOLLOWS]->(u2:User) MATCH (u2)-[f2:FOLLOWS]->(u1) where id(u1)>id(u2) 
             return u1.name, u2.name;
        """).values()


def query6(tx):
    """ List the top 10 of most followed users. """
    return tx.run("""
            MATCH (u1: User)-[f:FOLLOWS]->(u2:User) with COUNT(u2) as followers, u2 return u2.name, followers 
             order by followers desc limit 10;
        """).values()


def query7(tx):
    """List the top 10 authors with the highest average of book ratings."""
    return tx.run("""
            MATCH (a:Author)-[w:WROTE]->(b:Book) with ROUND(AVG(b.rating),2) as avg_book_rating, a return a.name, 
             avg_book_rating order by avg_book_rating desc limit 10;
        """).values()


def query8(tx):
    """ List the pair of authors which have shortest path of length equal to 5. """
    return tx.run("""
            match (a1: Author) match(a2: Author) where id(a1)>id(a2) and length(shortestpath((a1)-[*..100]-(a2)))=8
             return a1.name, a2.name;
        """).values()


def query9(tx):
    """List the authors which have wrote more than 5 books and order them by the number of books, from highest
    to lowest."""
    return tx.run("""
            match (a: Author)-[w:WROTE]->(b:Book) with count(b) as books, a where books > 5 return a.name, books 
             order by books desc;
        """).values()


def query10(tx):
    """List the 5 most recent books to be published."""
    return tx.run("""
            match (b:Book) with duration.inSeconds(datetime("2022-12-20"), datetime(b.publish_date)) 
             as duration, b return b.title order by duration desc limit 5;
        """).values()


def exec_read(func):
    with driver.session(database="books") as session:
        return session.execute_read(func)


def write_to_file(queries_questions, queries_solutions, queries):
    for i in range(len(queries_questions)):
        fh.write(queries_questions[i] + "\n\n")
        fh.write("Query Solution: \n")
        fh.write(queries_solutions[i] + "\n\n")
        fh.write("Query Output: \n")
        result = exec_read(queries[i])
        for res in result:
            fh.write(str(res) + "\n")
        fh.write("\n")


if __name__ == '__main__':
    uri = "bolt://localhost:7687"
    driver = GraphDatabase.driver(uri)

    fh = open("CBD_L44c_output.txt", "w")

    queries_question = ["Query #1 List all books written by J.R.R. Tolkien.",
                        "Query #2 List all books published by Penguin Books.",
                        "Query #3 List all books that were liked by the user with id equal to 15.",
                        "Query #4 List the top 10 of books with most pages.",
                        "Query #5 List the users that follow each other mutually.",
                        "Query #6 List the top 10 of most followed users.",
                        "Query #7 List the top 10 authors with the highest average of book ratings.",
                        "Query #8 List the pair of authors which have shortest path of length equal to 5.",
                        "Query #9 List the authors which have wrote more than 5 books and order "
                        "them by the number of books, from highest to lowest.",
                        "Query #10 List the 5 most recent books to be published."
                        ]

    queries_solution = ["MATCH (a:Author {name: 'J.R.R. Tolkien'})-[w:WROTE]->(b:Book) return b.title;",
                        "MATCH (p:Publisher {name: 'Penguin Books'})-[pub:PUBLISHED]->(b:Book) return b.title;",
                        "MATCH (u: User {id: 15})-[:LIKES]-(b:Book) return b.title;",
                        "MATCH (b:Book) return b.title, b.num_pages order by b.num_pages desc limit 10;",
                        "MATCH (u1: User)-[f1:FOLLOWS]->(u2:User) MATCH (u2)-[f2:FOLLOWS]->(u1) where id(u1)>id(u2)" 
                        " return u1.name, u2.name;",
                        "MATCH (u1: User)-[f:FOLLOWS]->(u2:User) with COUNT(u2) as followers, u2"
                        " return u2.name, followers order by followers desc limit 10;",
                        "MATCH (a:Author)-[w:WROTE]->(b:Book) with ROUND(AVG(b.rating),2) as avg_book_rating, a "
                        " return a.name, avg_book_rating order by avg_book_rating desc limit 10;",
                        "match (a1: Author) match (a2: Author) where id(a1)>id(a2) and length(shortestpath((a1)-[*..100]-(a2)))=8"
                        "return a1.name, a2.name;",
                        "match (a: Author)-[w:WROTE]->(b:Book) with count(b) as books, a"
                        " where books > 5 return a.name, books order by books desc;",
                        "match (b:Book) with duration.inSeconds(datetime('2022-12-20'), datetime(b.publish_date))"
                        " as duration, b return b.title order by duration desc limit 5;"
                        ]
    query = [query1, query2, query3, query4, query5, query6, query7, query8, query9, query10]

    write_to_file(queries_question, queries_solution, query)

