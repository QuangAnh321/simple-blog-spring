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
    document.getElementById("blog-content-summary-hidden").value = quill.getText().substring(0, 50);
    document.getElementById("blog-content-hidden").value = blogContentDeltaObj;
    document.getElementById("create-new-blog-form").submit();
}

function updateCurrentBlog() {
    const blogContentDeltaObj = JSON.stringify(quill.getContents());
    document.getElementById("blog-content-formatted-hidden").value = quill.root.innerHTML;
    document.getElementById("blog-content-summary-hidden").value = quill.getText().substring(0, 50);
    document.getElementById("blog-content-hidden").value = blogContentDeltaObj;
    document.getElementById("update-current-blog-form").submit();
}

function loadCurrentBlogContent(blogToUpdateCurrentContent) {
    quill.setContents(JSON.parse(blogToUpdateCurrentContent));
}
