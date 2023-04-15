"""TPW URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from booksApp import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('titles/', views.get_all_titles, name='get_all_titles'),
    path('home/', views.home, name='home'),
    path('books/', views.book_list, name='booklist'),
    path('book/<int:_id>/', views.get_book, name='book'),
    path('authors/', views.author_list, name='authorlist'),
    path('author/<int:_id>/', views.get_author, name='author'),
    path('author/<int:author_id>/books', views.book_list_by_author, name='author_booklist'),
    path('publishers/', views.publisher_list, name='publisherlist'),
    path('publisher/<int:_id>/', views.get_publisher, name='publisher'),
    path('publisher/<int:publisher_id>/books', views.book_list_by_publisher, name='publisher_booklist'),
    path('booksearch/', views.booksearch, name='booksearch'),
    path('bookquery/', views.bookquery, name='bookquery'),
    path('authorsearch/', views.authorsearch, name='authorsearch')

]
