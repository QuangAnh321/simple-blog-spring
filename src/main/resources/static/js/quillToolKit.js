var quill = new Quill('#editor', {
    modules: {
        toolbar: [
            [{ "font": [] }, { "size": ["small", false, "large", "huge"] }], // custom dropdown

            ["bold", "italic", "underline", "strike"],

            [{ "color": [] }, { "background": [] }],

            [{ "script": "sub" }, { "script": "super" }],

            [{ "header": 1 }, { "header": 2 }, "blockquote", "code-block"],

            [{ "list": "ordered" }, { "list": "bullet" }, { "indent": "-1" }, { "indent": "+1" }],

            [{ "direction": "rtl" }, { "align": [] }],

            ["link", "image", "video", "formula"],

            ["clean"]
        ]
    },
    theme: 'snow',
    placeholder: 'Your blog content goes here'
});

function createNewBlog() {
    const blogContentDeltaObj = JSON.stringify(quill.getContents());
    document.getElementById("blog-content-formatted-hidden").value = quill.root.innerHTML;
    document.getElementById("blog-content-summary-hidden").value = quill.getText().substring(0, 250);
    document.getElementById("blog-content-hidden").value = blogContentDeltaObj;
    document.getElementById("create-new-blog-form").submit();
}

function updateCurrentBlog() {
    const blogContentDeltaObj = JSON.stringify(quill.getContents());
    document.getElementById("blog-content-formatted-hidden").value = quill.root.innerHTML;
    document.getElementById("blog-content-summary-hidden").value = quill.getText().substring(0, 250);
    document.getElementById("blog-content-hidden").value = blogContentDeltaObj;
    document.getElementById("update-current-blog-form").submit();
}

function loadCurrentBlogContent(blogToUpdateCurrentContent) {
    quill.setContents(JSON.parse(blogToUpdateCurrentContent));
}

function deleteBlog() {
    const confirmText = "Are you sure you want to delete this blog ?";
    if (confirm(confirmText) == true) {
        document.getElementById("delete-blog-form").submit();
    } else {
        document.getElementById("delete-blog-form").addEventListener("click", function(event){
            event.preventDefault()
        });
    }
}
