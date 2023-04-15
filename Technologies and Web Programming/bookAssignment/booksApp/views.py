from django.shortcuts import render
from django.http import HttpRequest, HttpResponse
from booksApp.models import Book, Author, Publisher
from booksApp.forms import BookQueryForm
# Create your views here.


def get_all_titles(request):
    return HttpResponse("Hey")


def home(request):
    return render(request, 'index.html')


def book_list(request):
    book_selection = Book.objects.all()
    params = dict()
    params['title'] = "Book List"
    params['message'] = "These are the BookPlace books!"
    params['book_list'] = []
    for book in book_selection:
        params['book_list'].append({'book_title': book.title, 'book_url': f'/book/{book.id}'})

    return render(request, 'booklist.html', params)


def book_list_by_author(request, author_id):
    author = Author.objects.get(id=author_id)
    book_selection = Book.objects.filter(authors__id=author_id)
    params = dict()
    params['title'] = f"{author.name}'s Book List"
    params['message'] = f"These are the {author.name}'s  books!"
    params['book_list'] = []
    for book in book_selection:
        params['book_list'].append({'book_title': book.title, 'book_url': f'/book/{book.id}'})

    return render(request, 'booklist.html', params)


def get_book(request, _id):
    selected_book = Book.objects.get(id=_id)
    params = {'title': 'Book Details', 'book': selected_book}
    return render(request, 'book.html', params)


def author_list(request):
    author_selection = Author.objects.all()
    params = dict()
    params['title'] = "Author List"
    params['message'] = "These are the BookPlace Authors!"
    params['authors_list'] = []
    for author in author_selection:
        params['authors_list'].append({'author_name': author.name, 'author_url': f'/author/{author.id}',
                                       'books_url': f'/author/{author.id}/books'})

    return render(request, 'authorlist.html', params)


def get_author(request, _id):
    selected_author = Author.objects.get(id=_id)
    params = {'title': 'Author Details', 'author': selected_author}
    return render(request, 'author.html', params)


def publisher_list(request):
    publisher_selection = Publisher.objects.all()
    params = dict()
    params['title'] = "Publisher List"
    params['message'] = "These are the BookPlace Publishers!"
    params['publisher_list'] = []
    for publisher in publisher_selection:
        params['publisher_list'].append({'publisher_name': publisher.name,
                                         'publisher_url': f'/publisher/{publisher.id}',
                                        'books_url': f'/publisher/{publisher.id}/books'})

    return render(request, 'publisherlist.html', params)


def book_list_by_publisher(request, publisher_id):
    publisher = Publisher.objects.get(id=publisher_id)
    book_selection = Book.objects.filter(publisher__id=publisher_id)
    params = dict()
    params['title'] = f"{publisher.name}'s Book List"
    params['message'] = f"These are the {publisher.name}'s  books!"
    params['book_list'] = []
    for book in book_selection:
        params['book_list'].append({'book_title': book.title, 'book_url': f'/book/{book.id}'})

    return render(request, 'booklist.html', params)


def get_publisher(request, _id):
    selected_publisher = Publisher.objects.get(id=_id)
    params = {'title': 'Publisher Details', 'publisher': selected_publisher}
    return render(request, 'publisher.html', params)


def booksearch(request):
    if 'query' in request.POST:
        query = request.POST['query']
        if query:
            books = Book.objects.filter(title__icontains=query)
            return render(request, 'bookformlist.html', {'boks': books, 'query': query})
        else:
            return render(request, 'search.html', {'error': True})
    else:
        return render(request, 'search.html', {'error': False})


def authorsearch(request):
    if 'query' in request.POST:
        query = request.POST['query']
        if query:
            authors = Author.objects.filter(name__icontains=query)
            return render(request, 'authorformlist.html', {'authors': authors, 'query': query})
        else:
            return render(request, 'search.html', {'error': True})
    else:
        return render(request, 'search.html', {'error': False})

def bookquery(request):
    if request.method == 'POST':
        form = BookQueryForm(request.POST)
        if form.is_valid():
            query = form.cleaned_data['query']
            books = Book.objects.filter(title__icontains=query)
            return render(request, 'bookformlist.html', {'boks':books, 'query': query})
    else:
        form = BookQueryForm()
    return render(request, 'bookquery.html', {'form': form})