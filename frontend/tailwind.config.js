module.exports = {
    purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
    darkMode: 'class', // or 'media' or 'class'

    theme: {
        extend: {},

        //buttons, bg, light-bg, input, borders,
        colors: {
            'primary-light': '#60A5FA',
            'primary': '#6366F1', //indigo-500
            'primary-medium': '#4F46E5',
            'primary-darker': '#4338CA', //indigo-700

            'secondary': '',
            'secondary-dark': '',

            'primary-text': '#374151', //gray-700 or 900 ?
            'primary-text-dark': '#D1D5DB', //gray-300

            'btn-text': 'white',
            'btn-text-dark': 'white',

            'secondary-text': '#A0AEC0', //gray 500
            'secondary-text-dark': '#718096',

            'plc-text': '#9CA3AF', //gray-400
            'plc-text-dark': '#9CA3AF', // gray-400

            'border': '',
            'focus-border': '',

            'primary-bg': '#F9FAFB',
            'primary-bg-dark': '#111827',

            'secondary-bg': 'white',
            'secondary-bg-dark': '#1F2937', //gray-800

            'nav-item-bg': '#E5E7EB', //
            'nav-item-bg-dark': '#111827',

            'nav-hover': '#F9FAFB',
            'nav-hover-bg': '#374151',

            'nav-text-selected': '#111827', //gray-900
            'nav-text-selected-dark': 'white',

            'nav-item-hover-bg': '#F3F4F6',
            'nav-item-hover-bg-dark': '#374151',

            'nav-text': '#1F2937',
            'nav-text-dark': '#D1D5DB', //gray-300

            'nav-icon': '#6B7280', //gray-500
            'nav-icon-dark': '#9CA3AF', //gray-400

            'box': 'white',
            'box-dark': '#374151',

            'box-border': '#D1D5DB', //gray-300
            'box-border-dark': '#4B5563', //gray-600

            'timeline-border': '#E5E7EB',
            'timeline-border-dark': '#4B5563',
        }
    },
    variants: {
        extend: {},
    },
    plugins: [
        require('@themesberg/flowbite/plugin')
    ],
}