var quill = new Quill('#editor', {
    theme: 'snow',
    placeholder: 'Your blog content goes here'
});

function createNewBlog() {
    const blogContentDeltaObj = JSON.stringify(quill.getContents());
    document.getElementById("blog-content-summary-hidden").value = quill.getText().substring(0, 50);
    document.getElementById("blog-content-hidden").value = blogContentDeltaObj;
    document.getElementById("create-new-blog-form").submit();
}

function updateCurrentBlog() {
    const blogContentDeltaObj = JSON.stringify(quill.getContents());
    document.getElementById("blog-content-summary-hidden").value =quill.getText().substring(0, 50);
    document.getElementById("blog-content-hidden").value = blogContentDeltaObj;
    document.getElementById("update-current-blog-form").submit();
}

function loadCurrentBlogContent(blogToUpdateCurrentContent) {
    quill.setContents(JSON.parse(blogToUpdateCurrentContent));
}
