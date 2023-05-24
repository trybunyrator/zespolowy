import React, { useState, useEffect } from 'react';
import axios from 'axios';

function PostList() {
  const [posts, setPosts] = useState([]);
  const [newPost, setNewPost] = useState({
    comment: '',
    postContent: ''
  });

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      //Wyswietla wszystkie komentarze (Dodalem tą opcje w swingu) 
      //'http://localhost:8081/commentsPost/1' - Wyswietla 1 komentarz
      const response = await axios.get('http://localhost:8081/commentsPost');
      setPosts(response.data);
    } catch (error) {
      console.error('Błąd pobierania danych:', error);
    }
  };

  const handleInputChange = (e) => {
    setNewPost({ ...newPost, [e.target.name]: e.target.value });
  };

  //Proba dodania komentarza do bazy, ale nie ma posta wiec :)
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.post('http://localhost:8081/commentsPost', {
        comment: newPost.comment,
        postContent: newPost.postContent
      });

      setNewPost({ comment: '', postContent: '' });
      await fetchData(); // Odświeżenie danych po dodaniu nowego postu
    } catch (error) {
      console.error('Błąd dodawania postu:', error);
    }
  };

  return (
    <div>
      <h2>Lista postów:</h2>
      <ul>
        {posts.map((post) => (
          <li key={post.idComment}>
            <strong>ID komentarza:</strong> {post.idComment}, <br />
            <strong>Treść komentarza:</strong> {post.comment}, <br />
            <strong>ID postu:</strong> {post.idPost.idPost}, <br />
            <strong>Treść postu:</strong> {post.idPost.content}
          </li>
        ))}
      </ul>

      <h2>Dodaj nowy post:</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Komentarz:</label>
          <input
            type="text"
            name="comment"
            value={newPost.comment}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Treść postu:</label>
          <input
            type="text"
            name="postContent"
            value={newPost.postContent}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit">Dodaj post</button>
      </form>
    </div>
  );
}

function Data() {
  return (
    <div>
      <h1>Moja aplikacja React z pobieraniem i dodawaniem danych do bazy danych</h1>
      <PostList />
    </div>
  );
}

export default Data;
