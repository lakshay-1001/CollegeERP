export default function RoleSelection() {
  return (
    <div className="py-20 px-6 md:px-12 bg-gray-100 text-center">
      <h2 className="text-2xl md:text-3xl font-semibold text-gray-800 mb-10">
        Portal Access
      </h2>

      <div className="grid md:grid-cols-3 gap-6 max-w-5xl mx-auto">

        {/* Student */}
        <div className="p-6 bg-white border rounded-lg">
          <h3 className="font-semibold text-lg">Student Login</h3>
          <p className="text-gray-500 mt-2 text-sm">
            Access your classes and updates
          </p>
          <div className="mt-4 w-full py-2 rounded bg-blue-50 text-blue-800 text-sm">
            Use the Login button above
          </div>
        </div>

        {/* Teacher */}
        <div className="p-6 bg-white border rounded-lg">
          <h3 className="font-semibold text-lg">Teacher Login</h3>
          <p className="text-gray-500 mt-2 text-sm">
            Manage classes and communicate
          </p>
          <div className="mt-4 w-full py-2 rounded bg-green-50 text-green-800 text-sm">
            Use the Login button above
          </div>
        </div>

        {/* Admin */}
        <div className="p-6 bg-white border rounded-lg">
          <h3 className="font-semibold text-lg">Admin Panel</h3>
          <p className="text-gray-500 mt-2 text-sm">
            System management and control
          </p>
          <div className="mt-4 w-full py-2 rounded bg-gray-100 text-gray-800 text-sm">
            Use the Login button above
          </div>
        </div>

      </div>
    </div>
  );
}