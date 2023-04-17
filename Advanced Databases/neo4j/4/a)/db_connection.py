from neo4j import GraphDatabase

uri = "bolt://localhost:7687"
driver = GraphDatabase.driver(uri)


def create_node(tx, name):
    tx.run("CREATE (a:Person {name: $name})", name=name)


def get_node(tx, name):
    result = tx.run("MATCH (a:Person {name: $name}) RETURN a.name AS name", name=name).single()
    if result is None:
        return None
    else:
        return result[0]


def delete_node(tx, name):
    tx.run("MATCH (a:Person {name: $name})-[r]-() DELETE r", name=name)
    tx.run("MATCH (a:Person {name: $name}) DELETE a", name=name)


def update_node(tx, name, new_name):
    tx.run("MATCH (a:Person {name: $name}) SET a.name = $new_name", name=name, new_name=new_name)


def create_relationship(tx, name1, name2):
    tx.run("MATCH (a:Person {name: $name1}) MATCH (b:Person {name: $name2}) CREATE (a)-[:KNOWS]->(b)",
           name1=name1, name2=name2)


names = ["Joana", "Alfredo", "Carlos", "Catarina", "Arquimedes"]

# Create nodes

with driver.session(database="lab44") as session:
    for name in names:
        session.execute_write(create_node, name)

# Get nodes

with driver.session(database="lab44") as session:
    for name in names:
        print(session.execute_read(get_node, name))

# Create relationships

with driver.session(database="lab44") as session:
    session.execute_write(create_relationship, "Joana", "Alfredo")
    session.execute_write(create_relationship, "Joana", "Carlos")
    session.execute_write(create_relationship, "Joana", "Catarina")
    session.execute_write(create_relationship, "Joana", "Arquimedes")


# Update nodes

with driver.session(database="lab44") as session:
    for i, name in enumerate(names):
        session.execute_write(update_node, name, name + str(i))

# Delete nodes

# """""
with driver.session(database="lab44") as session:
    for i, name in enumerate(names):
        session.execute_write(delete_node, name + str(i))
# """""

driver.close()

