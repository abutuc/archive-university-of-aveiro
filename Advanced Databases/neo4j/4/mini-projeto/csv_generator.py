import csv
import random


def books_csv(raw_data_file, output_file):
    rows = []
    with open(raw_data_file) as csv_file:
        reader = csv.reader(csv_file)
        i = 0
        for r in reader:
            if i == 0:
                rows.append(["bookId", "title", "author", "rating", "num_pages", "publish_date", "publisher"])
                i = 1
            else:
                publish_date = r[10].split("/")
                publish_date = publish_date[2] + "-" + publish_date[0] + "-" + publish_date[1]
                rows.append([r[0], r[1], r[2].split("/")[0], r[3], r[7], publish_date, r[11]])

    with open(output_file, "w") as csv_output:
        writer = csv.writer(csv_output)
        writer.writerows(rows)

    print("Books, Authors and Publishers Generated Successfully.")


def users_csv(raw_data_users_file, output_file):
    names = set()
    # load name data into names list
    with open(raw_data_users_file[0]) as name_file:
        for line in name_file.readlines():
            names.add(line.strip())

    with open(raw_data_users_file[1]) as name_file:
        for line in name_file.readlines():
            names.add(line.strip())

    names = list(names)
    names_csv = [["userId", "name"]]
    n = 100
    for i in range(n):
        names_csv.append([i, names[random.randint(0, len(names)-1)]])

    with open(output_file, "w") as csv_output:
        writer = csv.writer(csv_output)
        writer.writerows(names_csv)

    print("Users Generated Successfully.")


def users_relation_csv(users_data, books_data, output_files):
    books = []
    authors = set()
    # load book data into books and authors list
    with open(books_data) as books_data:
        reader = csv.reader(books_data)
        i = 0
        for r in reader:
            if i == 0:
                i = 1
                continue
            else:
                books.append([r[1], r[3], r[4], r[5]])
                authors.add((r[2]))

    authors = list(authors)
    users = []

    with open(users_data) as users_data:
        reader = csv.reader(users_data)
        i = 0
        for r in reader:
            if i == 0:
                i = 1
                continue
            else:
                users.append([r[0], r[1]])

    users_users = [["userId1", "user1", "userId2", "user2"]]

    min_follows = 0
    max_follows = 10
    for i, user in enumerate(users):
        follows = random.randint(min_follows, max_follows)
        for f in range(follows):
            follow_index = random.randint(0, len(users)-1)
            if follow_index == i and i == 0:
                follow_index += 1
            elif follow_index == i and i == len(users)-1:
                follow_index -= 1
            users_users.append(user + [follow_index, users[follow_index][1]])

    users_books = [["userId", "user", "book_name", "book_rating", "book_num_pages", "book_publish_date"]]
    min_books = 0
    max_books = 20
    for user in users:
        number_of_books = random.randint(min_books, max_books)
        for b in range(number_of_books):
            book_index = random.randint(0, len(books)-1)
            users_books.append(user + books[book_index])

    users_authors = [["userId", "user", "author_name"]]
    min_authors = 0
    max_authors = 10
    for user in users:
        number_of_authors = random.randint(min_authors, max_authors)
        for a in range(number_of_authors):
            author_index = random.randint(0, len(authors)-1)
            users_authors.append(user + [authors[author_index]])

    for i, output_file in enumerate(output_files):
        with open(output_file, "w") as output:
            writer = csv.writer(output)
            if i == 0:
                writer.writerows(users_users)
            elif i == 1:
                writer.writerows(users_books)
            elif i == 2:
                writer.writerows(users_authors)
    print("User-User, User-Book, User-Author Relations Generated Successfully.")


if __name__ == '__main__':
    books_csv('raw_data/books.csv', 'data/books.csv')
    users_csv(['raw_data/female.txt', 'raw_data/male.txt'], 'data/users.csv')
    users_relation_csv('data/users.csv', 'data/books.csv',
                       ['data/users_users.csv', 'data/users_books.csv', 'data/users_authors.csv'])

